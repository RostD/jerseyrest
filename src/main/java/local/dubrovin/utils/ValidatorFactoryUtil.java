package local.dubrovin.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorFactoryUtil {
    private static Validator validator;
    public static Validator getValidator(){
        if(validator == null) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        return  validator;
    }
}
