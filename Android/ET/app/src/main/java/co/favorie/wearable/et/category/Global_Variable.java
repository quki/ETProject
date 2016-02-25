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
    public static int gloval_toeic_part5_sec =0;
    public static int gloval_toeic_part6_sec =0;
    public static int gloval_toeic_part7_sec =0;
    public static int gloval_opic_speaking_min =2;
    public static int gloval_opic_speaking_sec = 0;
    public static int gloval_opic_speaking_prepare=5;
    public static int gloval_opic_num=15;

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
    static int get_gloval_toeic_part5_sec(){return gloval_toeic_part5_sec;}
    static int get_gloval_toeic_part6_sec(){return gloval_toeic_part6_sec;}
    static int get_gloval_toeic_part7_sec(){return gloval_toeic_part7_sec;}
    static int get_gloval_opic_speaking_min(){return gloval_opic_speaking_min;}
    static int get_gloval_opic_speaking_sec(){return gloval_opic_speaking_sec;}
    static int get_gloval_opic_speaking_prepare(){return gloval_opic_speaking_prepare;}
    static int get_gloval_opic_num(){return gloval_opic_num;}

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

    static void set_gloval_toeic_part5(int part1, int part2 ){
        gloval_toeic_part5 = part1;
        gloval_toeic_part5_sec = part2;

    }
    static void set_gloval_toeic_part6(int part1, int part2 ){
        gloval_toeic_part6 = part1;
        gloval_toeic_part6_sec = part2;

    }
    static void set_gloval_toeic_part7(int part1, int part2){
        gloval_toeic_part7 = part1;
        gloval_toeic_part7_sec = part2;

    }


    static void set_gloval_opic(int min, int sec, int prepare ){
        gloval_opic_speaking_sec = sec;
        gloval_opic_speaking_min = min;
        gloval_opic_speaking_prepare = prepare;

    }



    static void reset_gloval_toeic_part5(){gloval_toeic_part5 =15; gloval_toeic_part5_sec =0;}
    static void reset_gloval_toeic_part6(){gloval_toeic_part6 =10; gloval_toeic_part6_sec =0;}
    static void reset_gloval_toeic_part7(){gloval_toeic_part7 =50; gloval_toeic_part7_sec =0;}

    static void reset_gloval_opic(){gloval_opic_speaking_min= 2; gloval_opic_speaking_sec=0; gloval_opic_speaking_prepare=2; gloval_opic_num=15;}
}
