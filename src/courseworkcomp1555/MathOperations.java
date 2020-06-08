package courseworkcomp1555;

import java.util.Arrays;

//This is the class that deals with all the mathematical calculations
//All the calculations in out program are performed dynamically
//We do not store values in global variables, so some methods call eachother to get specific values
//   e.g. the method RegressionLine(double[] x, double[] y) calls the methods yIntercept(double[] x, y) and (Slope(double[]x, y)
//    to get values for the slope and y intercept   
public class MathOperations {

    public MathOperations() {

    }
    //this method calculates the mean of an array

    public double mean(double[] a) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            // do summation
            sum += a[i];
        }
        return sum / (double) a.length;
    }

    public double Variance(double[] a) {                //this mehod is to calculate the variance
        //create loop to calculate diference between each element of the array and the mean and assign to diff
        //double sumOfSquares;

        double[] diff = new double[a.length];           //array to store the difference between x and x mean
        double sumOfSquares = 0;
        double[] squares = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            double diference = a[i] - mean(a);          //(x - x mean)
            diff[i] = diference;                        //asign each difference to array
            double sq = diference * diference;          //square the differente
            squares[i] = sq;                            //assign the squares to an array
            sumOfSquares = sumOfSquares + squares[i];   //do the sumation of squares
        }

        return sumOfSquares / a.length;                 // variance = (x-x mean)squared/n
    }

    public double StandardDeviation(double[] a) {
        return Math.sqrt(Variance(a));                  //standard deviation is the square root of the variance
    }

    //this method calculates the Slope using the formula   b1= [sum(x- x mean)(y- y mean)]/sum(x-x mean)squared
    public double Slope(double[] x, double[] y) {           //SLOPE (y=b0+b1x; where b1 is the slope)

        double[] tempX = new double[x.length];
        double[] tempY = new double[y.length];
        double Sxy = 0, Sxx = 0;
        for (int i = 0; i < x.length; i++) {
            tempX[i] = x[i] - mean(x);                  // (Xi- X mean)
            tempY[i] = y[i] - mean(y);                  //(Yi- Y mean)
            Sxy += tempX[i] * tempY[i];                 //sum[(Xi- X mean)(Yi - Y mean)]
            Sxx += tempX[i] * tempX[i];                 //sum(Xi- X mean)squared    
        }
        return Sxy / Sxx;
    }

    //this method calculates the y Intercept using the formula b0= Y mean - b1*Xmean
    public double yIntercept(double[] x, double[] y) {  //Y INTERCEPT  (y=b0+b1x; where a is the Y intercept)
        return (mean(y) - (Slope(x, y) * mean(x)));
    }

    //this method does the calculation of y=b0+b1x for each x,y pair 
    //      it returns an array containing the minimu and maxium result value of the equation
    //      this 2 values are used to plot the regression line on the graph
    public double[] RegressionLine(double[] x, double[] y) {
        double[] line = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            line[i] = yIntercept(x, y) + (Slope(x, y) * x[i]);     //y=b0+b1X
        }
        Arrays.sort(line);
        double[] temp = new double[2];
        temp[0] = line[0];
        temp[1] = line[line.length - 1];
        return temp;
    }

    //this method calculates the standard error of the estimate 
    //    we are using the formula (y-forcasted y)/y so the result is scaled to y
    public double[] StandardErrorOfEstimate(double[] x, double[] y) {
        double[] temp = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            temp[i] = (y[i] - forecastedY(x, y)[i]) / y[i];
        }
        for (int i = 0; i < temp.length; i++) {
            temp[i] = Math.abs(temp[i]);
        }
        return temp;
    }

    public double R2(double[] x, double[] y) {
        double Sxy = 0;
        double Syy = 0;
        double Sxx = 0;
        double[] tempX = new double[x.length];
        double[] tempY = new double[y.length];
        for (int i = 0; i < x.length; i++) {
            tempX[i] = x[i] - mean(x);                      // (Xi- X mean) 
            tempY[i] = y[i] - mean(y);                      //(Yi- Y mean)
            Sxy += (tempX[i] * tempY[i]);                   //sum[(Xi- X mean)(Yi - Y mean)]
            Syy += ((y[i] - mean(y)) * (y[i] - mean(y)));   //sum[(y-Ymean) * (y-Ymean)]squared
            Sxx += ((x[i] - mean(x)) * (x[i] - mean(x)));
        }
        return (Sxy * Sxy) / (Sxx * Syy);
    }

    public double R(double[] x, double[] y) {
        return Math.sqrt(R2(x, y));
    }

    //this method calculates the forcasted Y for the comparison town and returns an array
    //      this array will be used to populate the "Forecasted Y" column from the 
    //      "Table E, Forecasted Values"
    public double[] forecastedY(double[] x, double[] y) {
        double[] foreY = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            foreY[i] = (yIntercept(x, y) + (Slope(x, y) * x[i]));
        }
        return foreY;
    }

    // this method calculates the forcasted Y and returns a single value,
    //          we use this method to forecast a value on the graph
    public double singleForecastedY(double[] x, double[] y, double xi) {
        return (yIntercept(x, y) + (Slope(x, y) * xi));
    }
}
