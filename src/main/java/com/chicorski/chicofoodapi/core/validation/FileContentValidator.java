package com.chicorski.chicofoodapi.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentValidator implements ConstraintValidator<FileContent, MultipartFile> {

    private List<String> allowed;

    @Override
    public void initialize(FileContent constraintAnnotation) {
        this.allowed = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile == null || this.allowed.contains(multipartFile.getContentType());
    }
}
