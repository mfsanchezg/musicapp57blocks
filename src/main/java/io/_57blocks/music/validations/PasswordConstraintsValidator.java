package io._57blocks.music.validations;

import lombok.extern.slf4j.Slf4j;
import org.passay.PasswordValidator;
import org.passay.LengthRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.WhitespaceRule;
import org.passay.RuleResult;
import org.passay.PasswordData;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Slf4j
public class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(
                        //Length rule. Min 10 max 128 characters
                        new LengthRule(10, 128),

                        //At least one upper case letter
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),

                        //At least one lower case letter
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),

                        //At least one special characters
                        new CharacterRule(EnglishCharacterData.Special, 1),

                        new WhitespaceRule()
                )
        );

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        String errorMessage = passwordValidator.getMessages(result).stream().findFirst().get();

        log.error(errorMessage);

        //Sending one message each time failed validation.
        constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;

    }
}
