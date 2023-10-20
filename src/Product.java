import java.util.Optional;

public class Product extends Menu{
    double price;
    int same_product_count;
    int option_same_product_count;
    String option_name;
    double option_price;

    Boolean option;
    public Product(String name, String info, double price) {
        super(name, info);
        this.price = price;
        this.same_product_count = 1;
    }

    public void addOption(String name, double price){
        this.option_name = name;
        this.option_price = price;
        this.option = false;
        this.option_same_product_count = 1;
    }

    public void printOption(){
        System.out.println(this.name + " | " + this.price + " | " + this.info);
        System.out.println("위 메뉴에 어떤 옵션으로 추가하시겠습니까?");
        System.out.print("1. " + this.name + "(W " + this.price + ") | ");
        System.out.println("2. " + this.name + "(" + this.option_name + ")(W " + this.option_price + ")");
    }
    public void printProduct(String tmp) {
        if (this.option == true) {
            System.out.println(this.option_name + " | " + this.option_price + " | " + this.info);
        } else {
            System.out.println(this.name + " | " + this.price + " | " + this.info);
        }
    }

    public void printProduct(){
        if (this.option == true) {
            System.out.println(this.name + "(" + this.option_name + ") | " + this.option_price + " | " + this.same_product_count + "개 | " + " | " + this.info);
        } else {
            System.out.println(this.name + " | " + this.price + " | " + this.same_product_count + "개 | " + " | " + this.info);
        }
    }

    public double getPrice(){
        if(this.option == true){
            return this.option_price;
        }else {
            return this.price;
        }
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setSame_product_count(int i){
        same_product_count = i;
    }
    public int getSame_product_count() {
        if (this.option == true) {
            return this.option_same_product_count;
        } else {
            return this.same_product_count;
        }
    }

    public String getName(){
        if(this.option == true){
            return this.name + "(" + this.option_name +")";
        }else{
            return this.name;
        }
    }

    public Boolean getOption(){
        return this.option;
    }

    public void setOption(Boolean b){
        this.option = b;
    }
}
