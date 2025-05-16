package com.johnwilliam.ExpressoUnix.Entities.Objects;

public class Text {
    private String text;

    public Text(String text) {
        this.text = text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public void verifyLength(int limit){
        if(text.length()>limit){
            throw new IllegalArgumentException("O atributo ultrapassa o limite estabelecido");
        }

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
