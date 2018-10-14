package local.dubrovin.dao;

import local.dubrovin.utils.ValidatorFactoryUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

class AbstractDao {
    void validateModel(Object model) {
        Validator validator = ValidatorFactoryUtil.getValidator();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(model);

        if (constraintViolationSet.size() > 0) {
            throw new ValidateException(constraintViolationSet.iterator().next().getMessage());
        }
    }
}
