package com.audentest;

public class ShadowClass {
    public static double getShadowPointX(double primaryX, double primaryY, double secondaryX, double secondaryY,double r) {
        double term1 = primaryX * (r / Math.sqrt(Math.pow(primaryX, 2) + Math.pow(primaryY, 2)));
        double term2 = secondaryX * (r / Math.sqrt(Math.pow(secondaryX, 2) + Math.pow(secondaryY, 2)));
        
        double term3 = primaryY * (r / Math.sqrt(Math.pow(primaryX, 2) + Math.pow(primaryY, 2)));
        double term4 = secondaryY * (r / Math.sqrt(Math.pow(secondaryX, 2) + Math.pow(secondaryY, 2)));

        double term5 = (term2 + term1) / 2;
        double term6 = (term3 + term4) / 2;
        
        
        double result = term1 * (r / Math.sqrt(Math.pow(term5, 2) + Math.pow(term6, 2)));
        
        return result;
    }

    public static double getShadowPointY(double primaryX, double primaryY, double secondaryX, double secondaryY, double r) {
        double term1 = primaryY * (r / Math.sqrt(Math.pow(primaryX, 2) + Math.pow(primaryY, 2)));
        double term2 = secondaryY * (r / Math.sqrt(Math.pow(secondaryX, 2) + Math.pow(secondaryY, 2)));
        double term3 = primaryX * (r / Math.sqrt(Math.pow(primaryX, 2) + Math.pow(primaryY, 2)));
        double term4 = secondaryX * (r / Math.sqrt(Math.pow(secondaryX, 2) + Math.pow(secondaryY, 2)));
        
        double term5 = (term2 + term1) / 2;
        double term6 = (term3 + term4) / 2;
        
        double result = term1 * (r / Math.sqrt(Math.pow(term5, 2) + Math.pow(term6, 2)));
        
        return result;
    }
}
