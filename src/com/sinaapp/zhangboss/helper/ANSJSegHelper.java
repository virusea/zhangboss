package com.sinaapp.zhangboss.helper;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;

public class ANSJSegHelper {

	public static List<Term> parseStr(String str) {
		List<Term> parse = BaseAnalysis.parse(str);
	    return parse;
	}

}
