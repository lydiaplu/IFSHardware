package com.intlfoodsource.IFSHardware.utils;

public class CommonUtils {
    /**
     * Compares two objects that may be null for equality.
     * @param target1 The first object to compare.
     * @param target2 The second object to compare.
     * @return true if both objects are null or equal; false otherwise.
     * @param <T> The type of objects to compare.
     */
    public static <T> boolean equalsWithNullCheck(T target1, T target2) {
        if(target1 == null && target2 == null) {
            return true;
        }
        if(target1 == null || target2==null) {
            return false;
        }
        return target1.equals(target2);
    }
}
