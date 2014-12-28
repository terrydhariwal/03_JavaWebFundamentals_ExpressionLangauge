package io.nosqlyessql.mvc.model;

public class ApplicationCssSettings {


    private String ScriptletsAndJSPExpressions_CssClass = ""; //NOTE - for EL to work, member variables referenced in JSP need to start with lower case - even if this starts with uppercase!!!
    private String ExpressionsLanguage_CssClass = ""; //NOTE - for EL to work, member variables referenced in JSP need to start with lower case - even if this starts with uppercase!!!

    public String getScriptletsAndJSPExpressions_CssClass() {
        return ScriptletsAndJSPExpressions_CssClass;
    }

    public void setScriptletsAndJSPExpressions_CssClass(String scriptletsAndJSPExpressions_CssClass) {
        this.ScriptletsAndJSPExpressions_CssClass = scriptletsAndJSPExpressions_CssClass;
    }

    public String getExpressionsLanguage_CssClass() {
        return ExpressionsLanguage_CssClass;
    }

    public void setExpressionsLanguage_CssClass(String expressionsLanguage_CssClass) {
        this.ExpressionsLanguage_CssClass = expressionsLanguage_CssClass;
    }


}
