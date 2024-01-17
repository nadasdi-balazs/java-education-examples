package com.mindflytech.education.method;

import com.mindflytech.util.Utils;
import lombok.ToString;

public class DontUseMethodParameterAsOutputParameter {
    @ToString
    static class MutableStringHolder {
        private String string;

        MutableStringHolder(String string) {
            this.string = string;
        }

        public String getString() {
            System.out.println("-- getter called, holder returns: '" + string + "'");
            return string;
        }

        public void setString(String string) {
            System.out.println("-- setter called, holder string: '" + this.string
                    + "' will be overriden by new value: '" + string + "'");
            this.string = string;
        }
    }

    public String returnsStringAndModifiesStateOfIncomingArgument(MutableStringHolder mutable) {
        System.out.println("-- returnsStringAndModifiesStateOfIncomingArgument received: " + mutable);
        String valueStoredInMutable = mutable.getString();
        String prefix = Utils.generateCurrentDateTime() + "_";
        String result = prefix + valueStoredInMutable;
        //DON'T DO THAT: Command-Query Separation: this method looks like a query, it should not modify
        //the internal state of the parameters it has been called with 
        mutable.setString(result);
        return result;
    }

    public static void main(String[] args) {
        DontUseMethodParameterAsOutputParameter dontDoThat = new DontUseMethodParameterAsOutputParameter();
        MutableStringHolder argument = new MutableStringHolder("beginning");
        System.out.println("-- the argument I created originally: " + argument);
        String result = dontDoThat.returnsStringAndModifiesStateOfIncomingArgument(argument);
        System.out.println("-- method call resulted in: '" + result + "'");
        System.out.println("-- let's see if our argument is still the same: " + argument);
    }
}
