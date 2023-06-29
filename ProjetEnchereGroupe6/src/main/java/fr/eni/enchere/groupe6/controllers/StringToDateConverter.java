package fr.eni.enchere.groupe6.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.enchere.groupe6.bll.ArticleVenduServiceImpl;

@Component
public class StringToDateConverter implements Converter<String, Date> {

	
	@Override
	public Date convert(String source) {
        
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		
		try {
			date = formatter.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return date;
		
	}

}
