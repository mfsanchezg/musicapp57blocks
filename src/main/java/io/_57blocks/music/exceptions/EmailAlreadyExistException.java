package io._57blocks.music.exceptions;

import lombok.AllArgsConstructor;

public class EmailAlreadyExistException extends MusicAppException{

  public EmailAlreadyExistException(String message){
      super(message);
  }

}
