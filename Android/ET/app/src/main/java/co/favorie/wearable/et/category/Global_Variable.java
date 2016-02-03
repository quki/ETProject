package co.favorie.wearable.et.category;

import android.widget.Button;

/**
 * Created by Yohan on 2016-02-02.
 */


public  class Global_Variable {

    public static boolean global_toeic_part5=false;
    public static boolean global_toeic_part6=false;
    public static boolean global_toeic_part7=false;
    public static int gloval_toeic_part5 =15;
    public static int gloval_toeic_part6 =10;
    public static int gloval_toeic_part7 =50;
    public  Global_Variable(){}
    static boolean  get_global_toeic_part5(){
        return global_toeic_part5;
    }
    static boolean get_global_toeic_part6(){
        return global_toeic_part6;
    }
    static boolean get_global_toeic_part7(){
        return global_toeic_part7;
    }
    static int get_gloval_toeic_part5() { return gloval_toeic_part5; }
    static int get_gloval_toeic_part6() { return gloval_toeic_part6; }
    static int get_gloval_toeic_part7() { return gloval_toeic_part7; }




    static void set_global_toeic_part5(){
       if(global_toeic_part5 == false){
           global_toeic_part5 = true;
       }else{
           global_toeic_part5 = false;
       }
    }
    static void set_global_toeic_part6(){
        if(global_toeic_part6 == false){
            global_toeic_part6 = true;
        }else{
            global_toeic_part6 = false;
        }
    }
    static void set_global_toeic_part7(){
        if(global_toeic_part7 == false){
            global_toeic_part7 = true;
        }else{
            global_toeic_part7 = false;
        }
    }

    static void set_gloval_toeic_part5(int part ){
        gloval_toeic_part5 = part;

    }
    static void set_gloval_toeic_part6(int part ){
        gloval_toeic_part6 = part;

    }
    static void set_gloval_toeic_part7(int part ){
        gloval_toeic_part7 = part;

    }

}
