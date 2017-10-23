/**
 * Created by 周太宇 on 2017/9/14.
 */
public class HelloStruts {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String execute(){
        if("周".equals(name)){
            return "success";
        }else
        {
            return "index";
        }
    }
}
