package com.mindflytech.education.generics;

/*//from w ww  .  ja  v  a 2s .  c om
 * Licensed to the Indoqa Software Design und Beratung GmbH (Indoqa) under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Indoqa licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        Main main = new Main();
//        Main.
    }

    /**
     * Retrieve the type of a generic parameter of a class.<br>
     * <br>
     * This method will traverse the class hierarchy of <code>type</code> upwards until it encounters <code>declaringClass</code> and
     * then return the actual type argument for the parameter type at <code>parameterIndex</code>.
     *
     * @param type The class to be analyzed
     * @param declaringClass The class/interface declaring the generic parameter to be retrieved.
     * @param parameterIndex The index of the generic parameter at <code>declaringClass</code> to be retrieved.
     *
     * @return The actual class bound to the generic parameter.
     *
     * @throws RuntimeException If <code>type</code> is not a subclass of <code>declaringClass</code> or if
     *             <code>declaringClass</code> does not define at least <code>parameterIndex</code> + 1 generic parameters.
     */
    @SuppressWarnings("unchecked")
    public static Class<?> getGenericParameter(Class<?> type,
                                               Class<?> declaringClass, int parameterIndex) {
        ParameterizedType parameterizedType = getParameterizedType(type,
                declaringClass);
        if (parameterizedType == null) {
            throw new RuntimeException(declaringClass
                    + " does not define any generic parameters.");
        }

        Type[] actualTypes = parameterizedType.getActualTypeArguments();
        if (actualTypes.length <= parameterIndex) {
            throw new RuntimeException(
                    "Cannot retrieve parameter at index " + parameterIndex
                            + ". " + declaringClass + " defines only "
                            + declaringClass.getTypeParameters().length
                            + " parameters.");
        }

        Type actualType = actualTypes[parameterIndex];

        if (actualType instanceof Class) {
            return (Class<?>) actualType;
        }

        if (actualType instanceof TypeVariable) {
            // the generic parameter is bound at the subclass (or one of its subclasses)
            TypeVariable<GenericDeclaration> typeVariable = (TypeVariable<GenericDeclaration>) actualType;

            // get the generic definition binding this TypeVariable and find out the index it has there
            // then start another lookup
            GenericDeclaration genericDeclaration = typeVariable
                    .getGenericDeclaration();
            if (genericDeclaration instanceof Class) {
                int index = getIndex(genericDeclaration, typeVariable);
                return getGenericParameter(type,
                        (Class<?>) genericDeclaration, index);
            }
        }

        if (actualType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) actualType).getRawType();

            if (rawType instanceof Class) {
                return (Class<?>) rawType;
            }
        }

        throw new RuntimeException("Generic parameter " + actualType
                + " is not a class.");
    }
    /**
     * Retrieves the {@link ParameterizedType} that describes the parameter types bound to <code>declaringClass</code> in the type
     * hierarchy of <code>type</code>.
     *
     * @param type The class to be analyzed.
     * @param declaringClass The class/interface declaring generic parameters.
     *
     * @return The {@link ParameterizedType} requested or <code>null</code> if <code>declaringClass</code> does not define any generic
     *         parameters.
     *
     * @throws RuntimeException If <code>type</code> is not a subclass of <code>declaringClass</code>.
     */
    public static ParameterizedType getParameterizedType(Class<?> type,
                                                         Class<?> declaringClass) {
        if (!declaringClass.isAssignableFrom(type)) {
            throw new RuntimeException(type + " is not a subclass of "
                    + declaringClass + ".");
        }

        List<Type> types = new LinkedList<Type>();
        types.add(type);

        while (!types.isEmpty()) {
            Type currentType = types.remove(0);

            if (currentType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) currentType;

                if (parameterizedType.getRawType().equals(declaringClass)) {
                    return parameterizedType;
                }
            }

            types.addAll(getParents(currentType));
        }

        return null;
    }
    private static int getIndex(GenericDeclaration genericDeclaration,
                                TypeVariable<GenericDeclaration> typeVariable) {
        TypeVariable<?>[] typeParameters = genericDeclaration
                .getTypeParameters();

        for (int i = 0; i < typeParameters.length; i++) {
            TypeVariable<?> typeParameter = typeParameters[i];
            if (typeParameter.getName().equals(typeVariable.getName())) {
                return i;
            }
        }

        return -1;
    }
    private static Collection<Type> getParents(Type type) {
        Collection<Type> result = new LinkedList<Type>();

        if (type instanceof Class) {
            Class<?> classType = (Class<?>) type;
            result.add(classType.getGenericSuperclass());

            Type[] genericInterfaces = classType.getGenericInterfaces();
            for (Type eachGenericInterface : genericInterfaces) {
                result.add(eachGenericInterface);
            }
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return getParents(parameterizedType.getRawType());
        }

        return result;
    }
}
