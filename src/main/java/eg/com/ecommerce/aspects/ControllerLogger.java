package eg.com.ecommerce.aspects;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class ControllerLogger {

	private static final Logger logger = LoggerFactory.getLogger(ControllerLogger.class);

	private static final String POINTCUT = "within(eg.com.ecommerce.controller.*)";

	@Around(POINTCUT)
	public Object logArroundExec(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("before {}", constructLogMsg(pjp));
		Object proceed = pjp.proceed();
		logger.info("after {} wiht result: {}", constructLogMsg(pjp), proceed.toString());
		return proceed;
	}

	private String constructLogMsg(JoinPoint jp) {
		String args = Arrays.asList(jp.getArgs()).stream().map(String::valueOf)
				.collect(Collectors.joining(",", "[", "]"));
		Method method = ((MethodSignature) jp.getSignature()).getMethod();
		StringBuilder sb = new StringBuilder("@");
		sb.append(method.getName());
		sb.append(":");
		sb.append(args);
		return sb.toString();
	}
}
