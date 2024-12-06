package io.github.denkoch.hotel_booking_spring.graphql.exceptions;

import graphql.GraphQLError;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceAlreadyExistsException;
import io.github.denkoch.hotel_booking_spring.exceptions.ResourceNotFoundException;
import org.hibernate.LazyInitializationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleException(ResourceNotFoundException exception){
        return GraphQLError.newError().message(exception.getMessage()).build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleException(ResourceAlreadyExistsException exception){
        return GraphQLError.newError().message(exception.getMessage()).build();
    }

}
