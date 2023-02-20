package lk.ijse.dep10.util;

import java.util.Arrays;

public class DynamicArray {

    private int[] inputs = new int[0];


    public void add(String value) {
        int[] newInputs = new int[inputs.length +1];
        int i = 0;
        while (i < inputs.length){
            newInputs[i] = inputs[i];
            i++;
        }
        newInputs[newInputs.length-1]  = Integer.parseInt(value);
        inputs = newInputs;

    }

    public void clear() {
        inputs = new int[0];
    }

    public boolean contains(int value) {
        for (int input : inputs) {
            if(value == input){
                return true;
            }
        }
        return false;
    }

    public int size() {
        return inputs.length;
    }

    public String get(int index) {
        return inputs[index] + "";
    }

    public void remove(int index) {
        if(inputs.length ==0) return;
        System.out.println("removed");
        int [] removed = new int[inputs.length-1];
        int j = 0;
        for (int i = 0; i < inputs.length; i++) {
           if( i != index){
               removed[j] = inputs[i];
               j++;

           }

        }
        inputs = removed;



    }

    @Override
    public String toString() {
        return "DynamicArray{" +
                "inputs=" + Arrays.toString(inputs) +
                '}';
    }

    public int[] getInputs() {
        return inputs;
    }
}
