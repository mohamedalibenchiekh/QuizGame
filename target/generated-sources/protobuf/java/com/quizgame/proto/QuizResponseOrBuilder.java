// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: quizgame.proto

package com.quizgame.proto;

public interface QuizResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:quizgame.QuizResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string questions = 1;</code>
   * @return A list containing the questions.
   */
  java.util.List<java.lang.String>
      getQuestionsList();
  /**
   * <code>repeated string questions = 1;</code>
   * @return The count of questions.
   */
  int getQuestionsCount();
  /**
   * <code>repeated string questions = 1;</code>
   * @param index The index of the element to return.
   * @return The questions at the given index.
   */
  java.lang.String getQuestions(int index);
  /**
   * <code>repeated string questions = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the questions at the given index.
   */
  com.google.protobuf.ByteString
      getQuestionsBytes(int index);
}
