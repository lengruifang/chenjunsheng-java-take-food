import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    private ItemRepository itemRepository;
    private SalesPromotionRepository salesPromotionRepository;

    public App(ItemRepository itemRepository, SalesPromotionRepository salesPromotionRepository) {
        this.itemRepository = itemRepository;
        this.salesPromotionRepository = salesPromotionRepository;
    }
    public String bestCharge(List<String> inputs) {
        String[] Strings=new String[]{"============= Order details =============\n",
                "-----------------------------------\n","Promotion used:\n",
                "==================================="};
        StringJoiner result=new StringJoiner("");
         int priceSum = 0;
         int SOME_ITEM_HALF = 0;
        result.add(Strings[0]);
        for (String inputsString:inputs) {
            for (Item item : this.itemRepository.findAll()) {
                if (item.getId().equals(inputsString.substring(0,8))) {
                    result.add(item.getName() + " x " + inputsString.substring(11) + " = " + (int) (item.getPrice() * Integer.valueOf(inputsString.substring(11))) + " yuan\n");
                    priceSum += (int) (item.getPrice() * (Integer.valueOf(inputsString.substring(11))));
                    SOME_ITEM_HALF += (int) (item.getPrice() * (Integer.valueOf(inputsString.substring(11))));
                        if (this.salesPromotionRepository.findAll().get(1).getRelatedItems().contains(item.getId())){
                            SOME_ITEM_HALF -= (int) (item.getPrice() / 2 * (Integer.valueOf(inputsString.substring(11))));
                        }
                    if (inputsString.equals(inputs.get(inputs.size() - 1))) {
                        result.add(Strings[1]);
                    }
                }
            }
        }
        int BUY_30_SAVE_6 = priceSum;
        while (BUY_30_SAVE_6>30){
            BUY_30_SAVE_6-=6;
        }
        if (30 > priceSum&& priceSum< SOME_ITEM_HALF){
            result.add("Total："+ priceSum+" yuan\n");
        }else if (BUY_30_SAVE_6<= SOME_ITEM_HALF){
            result.add(Strings[2]);
            result.add("满30减6 yuan，saving "+(priceSum-BUY_30_SAVE_6)+" yuan\n");
            result.add(Strings[1]);
            result.add("Total："+BUY_30_SAVE_6+" yuan\n");
        }else {
            result.add(Strings[2]);
            result.add("Half price for certain dishes (Braised chicken，Cold noodles)，saving "+(priceSum - SOME_ITEM_HALF)+" yuan\n");
            result.add(Strings[1]);
            result.add("Total："+ SOME_ITEM_HALF +" yuan\n");
        }
        result.add(Strings[3]);
        //StringJoiner result=new StringJoiner("+");
        return result.toString();
        //TODO: write code here
    }
}
