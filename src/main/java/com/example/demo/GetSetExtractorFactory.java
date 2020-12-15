package com.example.demo;

import org.springframework.jdbc.core.ResultSetExtractor;

import dao.mapper.ClassicQuestion;
import dao.mapper.QuestionAnswersJoinResultSetExtractor;
import dao.mapper.QuestionFalsePerc;
import dao.mapper.QuestionResultSetExtractor;
import dao.mapper.UserResultSetExtractor;
import dao.mapper.UsersAnsweredSetExtractor;
import dao.mapper.UsersSortedSetExtractor;

public class GetSetExtractorFactory {

	@SuppressWarnings("rawtypes")
	public ResultSetExtractor getExtractor(String extractorType) {
		if(extractorType == null)
			return null;
		if(extractorType.contentEquals("UsersAnsweredSetExtractor"))
			return new UsersAnsweredSetExtractor();
		if(extractorType.contentEquals("Question"))
			return new QuestionResultSetExtractor();
		if(extractorType.contentEquals("QuestionFalsePerc"))
			return new QuestionFalsePerc();
		if(extractorType.contentEquals("UsersSortedSetExtractor"))
			return new UsersSortedSetExtractor();
		if(extractorType.contentEquals("ClassicQuestion"))
			return new ClassicQuestion();
		if(extractorType.contentEquals("QuestionAnswersJoin"))
			return new QuestionAnswersJoinResultSetExtractor();
		
		return new UserResultSetExtractor();
	}
}