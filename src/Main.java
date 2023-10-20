// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Menu myMenu = new Menu("myMenu", "메뉴", new Order("장바구니", "장바구니입니다."));
        myMenu.addMenu("햄버거", "맛있는 햄버거입니당");
        myMenu.addMenu("아이스크림", "맛있는 아이스크림입니당");
        myMenu.addMenu("음료수", "맛있는 음료수입니당");
        myMenu.addProduct("햄버거", "모짜렐라치즈버거", "맛있는 치즈버거", 4.8, "double", 7.0);
        myMenu.addProduct("햄버거", "빅맥", "맛있는 짱큰버거", 5.6, "double", 7.8);
        myMenu.addProduct("햄버거", "데리버거", "맛있는 데리버거", 3.6, "double", 5.8);
        myMenu.addProduct("아이스크림", "딸기 아이스크림", "맛있는 딸기 아이스크림", 2.5,"Large", 4.0);
        myMenu.addProduct("아이스크림", "바닐라 아이스크림", "맛있는 바닐라 아이스크림", 2.5, "Large", 4.0);
        myMenu.addProduct("음료수", "초코칩 프라페", "맛있는 초코칩 프라페", 4.8,"Large",6.8);
        myMenu.addProduct("음료수", "딸기 요거트 스무디", "맛있는 딸기 요거트 스무디", 5.5, "Large", 7.5);

        myMenu.onBoard();
    }
}