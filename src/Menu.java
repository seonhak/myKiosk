import java.util.Scanner;

public class Menu {
    String name;
    String info;
    Menu[] menus;
    Product[] products;
    Order order;
    int count;
    public Menu(String name, String info, Product[] products){
        this.name = name;
        this.info = info;
        this.products = products;
    }
    public Menu(String name, String info, Order order){
        this.name = name;
        this.info = info;
        count = 0;
        this.menus = new Menu[10];
        this.order = order;
    }
    public Menu(String name, String info){
        this.name = name;
        this.info = info;
        count = 0;
        this.products = new Product[10];
    }
    public void onBoard(){
        System.out.println("[  SONAK'S MENU  ]");
        int i;
        for(i = 0; i < this.count; i ++){
            System.out.print(i+1 + ". ");
            menus[i].printMenu();
        }
        System.out.println("[  ORDER MENU  ]");
        System.out.println(i + 1 + ". 장바구니");
        System.out.println(i + 2 + ". 주문취소");
        System.out.println("\n0을 입력하면 총 판매 현황을 조회합니다.");
        while(true){
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if(select > this.count+2){
                System.out.println("올바른 번호를 입력해주세요");
            }else if(select == count + 1){
                order.printOrder(this);
            }else if(select == count + 2){
                order.cancleOrder(this);

            }else if(select == 0){
                order.printCompletedOrder(this);
            }else{
                menus[select-1].onBoardProduct(this.order, this);
            }
        }
    }
    public void onBoardProduct(Order o, Menu m){
        System.out.println(this.getName() + "메뉴를 출력합니다.");
        for(int i = 0; i < this.count; i ++){
            System.out.print(i + 1 + ". ");
            products[i].printProduct("noCount");
        }
        System.out.println("처음으로 돌아가려면 0번을 입력해주세요.");
        while(true){
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if(select > this.count){
                System.out.println("올바른 번호를 입력해주세요");
            }else if(select == 0) {
                m.onBoard();
            }else{
                o.addOrder(products[select-1], m, this);
            }

        }

    }
    public void addMenu(String name, String info){
        this.menus[count] = new Menu(name, info);
        this.count ++;
    }

    public void addProduct(String menu_name, String name, String info, double price, String option_name, double option_price){
        int i;
        for(i = 0; i < this.count; i ++){
            if(menu_name == menus[i].getName()){
                break;
            }
        }
        Product pd = new Product(name, info, price);
        pd.addOption(option_name, option_price);
        menus[i].products[menus[i].count] = pd;
        menus[i].count ++;
    }

    public void printMenu(){
        System.out.println(this.name + " | " + this.info);
    }

//    set,get
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return this.info;
    }
    public Product[] getProducts(){
        return this.products;
    }
    public Menu[] getMenus(){
        return this.menus;
    }

    public int getCount(){
        return this.count;
    }
}
