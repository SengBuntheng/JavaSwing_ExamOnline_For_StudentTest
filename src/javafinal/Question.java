package javafinal;


import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bunth
 */
public class Question {
   private  String level;
   private String questionText;
   private String answer;
   private String[] options;

   public Question(String questionText , String answer , String[] options , String level){
       this.questionText = questionText;
       this.answer = answer;
       this.level = level;
       this.options = options;

   }

   public String questionText(){
       return questionText;

   }

   public String level(){
       return level;
   }
   public String answer(){
       return answer;
   }
   public String[] option (){
       return options;
   }

}
