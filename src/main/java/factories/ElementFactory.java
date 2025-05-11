package factories;

import elements.BaseElement;
import elements.ButtonElement;
import elements.LabelElement;
import elements.TextElement;
import enums.ElementType;
import org.openqa.selenium.By;

public class ElementFactory {
    public static BaseElement getElement(ElementType type, String label, By by){
        switch(type){
            case TEXTFIELD:{
                return new TextElement(label,by);
            }
            case LABEL:{
                return new LabelElement(label,by);
            }
            case BUTTON:{
                return new ButtonElement(label,by);
            }
            default:
                return new BaseElement(label,by);
        }
    }
}
