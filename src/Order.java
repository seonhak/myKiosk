import javax.swing.text.StyledEditorKit;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Order extends Menu {
    double total_price;
    int orderCount;
    Order completed_order;

    public Order(String name, String info) {
        super(name, info, new Product[20]);
        total_price = 0;
        orderCount = 1;
        completed_order = new Order();
    }

    public Order() {
        super("[ 총 판매상품 목록 현황 ]", "현재까지 총 판매된 상품 목록은 아래와 같습니다.", new Product[100]);
    }

    public void printOrder(Menu m) {
        int select;
        if (super.count == 0) {
            System.out.println("현재 장바구니에 상품이 없습니다.");
            Scanner sc = new Scanner(System.in);
            System.out.println("처음으로 돌아가려면 0번을 입력해주세요.");
            select = sc.nextInt();
            if (select == 0) {
                m.onBoard();
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
        System.out.println("[ ORDERS ]\n아래와 같이 주문하시겠습니까?");

        for (int i = 0; i < super.count; i++) {
            if (super.products[i] == null) {
                break;
            }
            System.out.print(i + 1 + ". ");
            super.products[i].printProduct();
        }

        System.out.println("총 금액 : " + this.total_price);
        System.out.println("\n1. 주문  2. 메뉴판");
        while (true) {
            Scanner sc = new Scanner(System.in);
            select = sc.nextInt();
            if (select == 1) {
                this.completeOrder(m);
            } else if (select == 2) {
                m.onBoard();
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }

    public void addOrder(Product product, Menu m, Menu selectedMenu) {
        Product pd = new Product(product.name, product.info, product.price);
        pd.addOption(product.option_name, product.option_price);
        pd.printOption();
        Scanner sc = new Scanner(System.in);
        int select = sc.nextInt();
        if (select == 1) {
            pd.option = false;
        } else if (select == 2) {
            pd.option = true;
        }

        System.out.println(pd.getName() + "상품을 정말로 추가하시겠어요?");
        System.out.println("1. 확인 2. 취소");
        select = sc.nextInt();
        if (select == 1) {
            Boolean isSame = false;
            for (int i = 0; i < super.count; i++) {
                if (pd.getName() == super.products[i].getName() && pd.getOption() == super.products[i].getOption()) {
                    isSame = true;
                    super.products[i].setSame_product_count(super.products[i].getSame_product_count() + 1);
                }
            }
            if (isSame) {
                System.out.println(pd.getName() + "이 장바구니에 추가됐어요!");
                total_price += pd.getPrice();
                total_price = (double) Math.round(total_price * 100) / 100;
            } else {
                super.products[super.count] = pd;
                System.out.println(pd.getName() + "이 장바구니에 추가됐어요!");
                super.count++;
                total_price += pd.getPrice();
                total_price = (double) Math.round(total_price * 100) / 100;
            }

            m.onBoard();

        } else if (select == 2) {
            System.out.println("메뉴 선택 창으로 돌아갑니다.");
            selectedMenu.onBoardProduct(m.order, m);
        } else {
            System.out.println("올바른 번호를 입력해주세요.");
        }

    }

    public void cancleOrder(Menu m) {
        int select;
        System.out.println("취소할 상품의 번호를 입력해주세요.");
        int i;
        for (i = 0; i < super.count; i++) {
            if (super.products[i] == null) {
                break;
            }
            System.out.print(i + 1 + ". ");
            super.products[i].printProduct();
        }
        System.out.println(i + 1 + ". 주문 전체 취소");
        System.out.println("처음으로 돌아가려면 0번을 입력해주세요.");
        while (true) {
            Scanner sc = new Scanner(System.in);
            select = sc.nextInt();
            if (select > super.count + 1) {
                System.out.println("올바른 번호를 입력해주세요.");
            } else if (select == 0) {
                m.onBoard();
            } else if (select == super.count + 1) {
                System.out.println("주문을 초기화 합니다.");
                initOrder();
                m.onBoard();
            } else {
                for (i = select - 1; i < super.count; i++) {
                    if (super.products[i + 1] == null) {
                        break;
                    }
                    super.products[i] = super.products[i + 1];
                }
                total_price -= super.products[select - 1].getPrice() * super.products[select - 1].getSame_product_count();
                total_price = (double) Math.round(total_price * 100) / 100;
                System.out.println(super.products[i].getName() + "상품을 취소합니다.");
                super.count--;
                super.products[select - 1].setSame_product_count(1);
                m.onBoard();

            }
        }
    }

    public void completeOrder(Menu m) {
        System.out.println("주문이 완료되었습니다!");
        System.out.println("대기번호는 [ " + this.orderCount + " ]번입니다.");
        System.out.println("3초 후 메뉴판으로 돌아갑니다.");


        completed_order.addCompletedOrder(this);
        initOrder();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.orderCount++;
        m.onBoard();
    }

    public void addCompletedOrder(Order o) {
        for (int i = 0; i < o.count; i++) {
            this.products[count] = o.products[i];
            count++;
            this.total_price += o.products[i].getPrice();
        }
    }

    public void initOrder() {
        for (int i = 0; i < super.count; i++) {
            super.products[i].setSame_product_count(1);
        }
        this.total_price = 0;
        super.count = 0;
        for (int i = 0; i < super.count; i++) {
            if (super.products[i] == null) {
                break;
            }
            super.products[i] = null;
        }
    }

    public void printCompletedOrder(Menu m) {
        System.out.println(this.completed_order.getName());
        System.out.println(this.completed_order.getInfo());
        System.out.println();
        for (int i = 0; i < this.completed_order.count; i++) {
            if (this.completed_order.products[i] == null) {
                break;
            }
            System.out.print(i + 1 + ". ");
            this.completed_order.products[i].printProduct();
        }
        System.out.println("현재까지 누적 판매 금액 : " + this.completed_order.total_price);
        System.out.println("처음으로 돌아가려면 1을 입력해주세요.");
        while (true) {
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if (select == 1) {
                m.onBoard();
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }
}
