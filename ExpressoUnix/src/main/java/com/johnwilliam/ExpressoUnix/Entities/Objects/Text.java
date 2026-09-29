package com.johnwilliam.ExpressoUnix.Entities.Objects;

import com.johnwilliam.ExpressoUnix.Exceptions.BusinessException;

public class Text {
    private String text;

    public Text(String text) {
        this.text = text;
    }
    public String getValue(){
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void verifyLength(int limit){
        if(text.length()>limit){
            throw new BusinessException("O atributo ultrapassa o limite estabelecido");
        }

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
