package com.mindflytech.education.oop.object;

public class SpringCglibObjectProxyImitation {

}

class ObjectToBeProxied {
    private int intField;

    public ObjectToBeProxied(int intField) {
        this.intField = intField;
        System.out.println("-- OverridenCloneMethodCloneable.init called, created object: " + this);
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }
}


class ProxyObject extends  ObjectToBeProxied {
    public ProxyObject(int intField) {
        super(intField);
        constuctorExitPointcut();
    }

    private void constuctorExitPointcut() {
        //so far we don't do anything here
    }

    public int getIntField() {
        getIntFieldEntryPointcut();
        int result = super.getIntField();
        getIntFieldExitPointcut(result);
        return result;
    }

    private void getIntFieldExitPointcut(int result) {
        //so far we don't do anything here
    }

    private void getIntFieldEntryPointcut() {
        //so far we don't do anything here
    }

    public void setIntField(int intField) {
        super.setIntField(intField);
    }
}
