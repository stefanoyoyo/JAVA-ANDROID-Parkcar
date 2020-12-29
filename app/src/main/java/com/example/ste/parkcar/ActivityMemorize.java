package com.example.ste.parkcar;

import java.util.Objects;

/**
 * Created by ament on 27/07/2017.
 *
 * This class is used to memorize the class before the current visited one.
 * The user will be able this way to return to the previous class
 *
 */

public class ActivityMemorize {
    private Object previousClass;
    private Object actualClass;


//----------------------------------------
    /**
     * Method used to set the activity the user will reach if he/she will press the indietro button
     *
     * @param obj new class to show if indietro is pressed
     */
    public void setPreviousClass (Object obj) {
        previousClass = obj;
    }

//----------------------------------------
    /**
     * Method used to see the previous class
     *
     * @return the previous class of the current one
     */
    public Object getpreviousClass () {
        return previousClass;
    }

//----------------------------------------

    /**
     * Method used to set WHICH activity the user will reach if he/she will press the indietro button
     *
     * @param obj new class to show if indietro is pressed
     */
    public void setActualClass (Object obj) {
        actualClass = obj;
    }

//----------------------------------------
    /**
     * Method used to see the previous class
     *
     * @return the previous class of the current one
     */
    public Object getActualClasss () {
        return actualClass;
    }

//----------------------------------------

    /**
     * This method has to be called at the beginning of the code of any different class method.
     * It allows to set the actual class and to set the previous one as the incoming class.
     *
     * @param obj new class to set as actual class
     */
    public void setClasses (Object obj) {
        previousClass = actualClass;
        actualClass = obj;
    }

    //----------------------------------------

    /**
     * This method allows to set the classes the way the programmer wants to.
     * For example, i may want to set as previous class, another activity instead of the real previous one.
     *
     * @param obj1 to set the previous class
     * @param obj2 to set the current class
     */
    public void setBothClasses (Object obj1, Object obj2) {
        previousClass = obj1;
        actualClass = obj2;
    }
}

