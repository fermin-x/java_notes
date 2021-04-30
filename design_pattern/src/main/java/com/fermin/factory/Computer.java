package com.fermin.factory;

/**
 * super class in design pattern can be interface , abstract class or normal class.
 */
public abstract class Computer {
    protected String ram;
    protected String hdd;
    protected String cpu;

    public abstract String getARM();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return "ARM = " + this.getARM() + ", HDD = " + this.getHDD() + ", CPU = " + this.getCPU();
    }
}

class PC extends Computer {

    @Override
    public String getARM() {
        return this.ram;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}
