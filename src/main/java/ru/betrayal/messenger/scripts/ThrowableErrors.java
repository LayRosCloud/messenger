package ru.betrayal.messenger.scripts;

public class ThrowableErrors {
    public static NotFoundException throwNotFoundException(String id) throws NotFoundException{
        return new NotFoundException(
                String.format("Exception! Object with id = `%s` not founded", id)
        );
    }

    public static NotFoundException throwNotFoundException(String message, Object ...args) throws NotFoundException{
        return new NotFoundException(
                String.format(message, args)
        );
    }

    public static BadRequestException throwBadRequestException(String message){
        return new BadRequestException(
                String.format(message)
        );
    }

    public static BadRequestException throwBadRequestException(String message, Object ...args){
        return new BadRequestException(
                String.format(message, args)
        );
    }
}
