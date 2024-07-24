import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderManagement {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private static final int BUCKET_DAYS = 3;
    // This will be used to assign Bucket
    private static final Date REF_DATE;

    static {
        try {
            REF_DATE = sdf.parse("15-07-2024");
        } catch (ParseException e) {
            throw new RuntimeException("Invalid reference date format.");
        }
    }


    public static void main(String[] args) throws ParseException {

        // Loading Dataset from given data

        List<Order> orders = Arrays.asList(
                new Order(1, 1200, "ABCD1", sdf.parse("10-07-2024"), 48, "P6"),
                new Order(2, 1175, "ABCD2", sdf.parse("15-07-2024"), 48, "P6"),
                new Order(3, 1160, "ABCD3", sdf.parse("05-07-2024"), 350, "P5"),
                new Order(4, 1122, "ABCD4", sdf.parse("05-07-2024"), 350, "P5"),
                new Order(5, 1100, "ABCD5", sdf.parse("12-07-2024"), 40, "P2"),
                new Order(5, 1080, "ABCD6", sdf.parse("12-07-2024"), 40, "P2"),
                new Order(5, 975, "ABCD7", sdf.parse("12-07-2024"), 40, "P2"),
                new Order(5, 925, "ABCD8", sdf.parse("12-07-2024"), 40, "P2")
        );

        List<GradeDetail> gradeDetails = Arrays.asList(
                new GradeDetail("ABCD1", "AG15", "PG18", "VD", "DG6", "M2", "G1"),
                new GradeDetail("ABCD2", "AG15", "PG18", "VD", "DG6", "M2", "G1"),
                new GradeDetail("ABCD3", "AG15", "PG18", "VD", "DG3", "M2", "G1"),
                new GradeDetail("ABCD4", "AG15", "PG18", "VD", "DG3", "M2", "G3")
                ,new GradeDetail("ABCD5", "AG15", "PG18", "VD", "DG3", "M2", "G3")
                ,new GradeDetail("ABCD6", "AG15", "PG18", "VD", "DG3", "M2", "G3")
                ,new GradeDetail("ABCD7", "AG15", "PG18", "VD", "DG3", "M2", "G3")
                ,new GradeDetail("ABCD8", "AG15", "PG18", "VD", "DG3", "M2", "G3")
        );

        List<GradeMix> gradeMixes = Arrays.asList(
                new GradeMix("ABCD1", "ABCD5"),
                new GradeMix("ABCD2", "ABCD6"),
                new GradeMix("ABCD3", "ABCD7"),
                new GradeMix("ABCD4", "ABCD8")
        );

        // Start Processing The data for each orders
        calculateBTCQty(orders);
        assignBuckets(orders, REF_DATE);
        assignL1Groups(orders, gradeMixes);
        calculateSetWidths(orders);
        generateL2Groups(orders);
        generateL3Groups(orders);
        generateL4Groups(orders, gradeDetails);
        mapGradeDetailsToOrders(orders,gradeDetails);



        printOrders(orders);

    }



    private static void calculateBTCQty(List<Order> orders) {
        for(Order order : orders)
        {
            order.setBtcQty(Math.round(order.getBtrQty()*1.1 * 100.0) / 100.0);
        }
    }

    private static void assignBuckets(List<Order> orders, Date refDate) {
        for (Order order : orders) {
            long diff = (refDate.getTime() - order.getDeliveryDate().getTime()) / (1000 * 60 * 60 * 24);
            order.setBucket((int) Math.ceil(diff / (double) BUCKET_DAYS));
        }
    }

    private static void calculateSetWidths(List<Order> orders) {
        for (Order order : orders) {
            order.setSetWidth((int) Math.ceil(order.getOrderWidth() / 25.0) * 25);
        }
    }

    private static void assignL1Groups(List<Order> orders, List<GradeMix> gradeMixes) {
        Map<String, String> gradeGroupMap = new HashMap<>();
        int groupCounter = 1;

        for (GradeMix mix : gradeMixes) {
            if (!gradeGroupMap.containsKey(mix.getOrderGrade())) {
                String group = "L1G" + groupCounter++;
                gradeGroupMap.put(mix.getOrderGrade(), group);
                gradeGroupMap.put(mix.getMixingPossible(), group);
            } else {
                String group = gradeGroupMap.get(mix.getOrderGrade());
                gradeGroupMap.put(mix.getMixingPossible(), group);
            }
        }

        for (Order order : orders) {
            order.setL1Group(gradeGroupMap.getOrDefault(order.getGrade(), "L1G" + groupCounter++));
        }
    }

    private static void generateL2Groups(List<Order> orders) {
        Map<String, List<Order>> l1GroupMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getL1Group));

        int l2GroupCounter = 1;

        for (List<Order> l1GroupOrders : l1GroupMap.values()) {
            l1GroupOrders.sort(Comparator.comparingInt(Order::getOrderWidth).reversed());
            int previousWidth = -1;

            for (Order order : l1GroupOrders) {
                if (previousWidth == -1 || order.getOrderWidth() - previousWidth <= 50
                        || order.getOrderWidth() +25+15 - previousWidth <= 45) {
                    order.setL2Group("L2G" + l2GroupCounter);
                } else {
                    l2GroupCounter++;
                    order.setL2Group("L2G" + l2GroupCounter);
                }
                previousWidth = order.getOrderWidth();
            }
            l2GroupCounter++;
        }
    }

    private static void generateL3Groups(List<Order> orders) {
        Map<String, List<Order>> l2GroupMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getL2Group));

        int l3GroupCounter = 1;

        for (List<Order> l2GroupOrders : l2GroupMap.values()) {
            l2GroupOrders.sort(Comparator.comparingInt(Order::getSetWidth));
            int minWidth = l2GroupOrders.get(0).getSetWidth();
            int maxWidth = l2GroupOrders.get(0).getSetWidth();
            l2GroupOrders.get(0).setL3Group("L3G" + l3GroupCounter);

            for (int i = 1; i < l2GroupOrders.size(); i++) {
                Order order = l2GroupOrders.get(i);
                if (order.getSetWidth() - minWidth > 75 || maxWidth - order.getSetWidth() > 75) {
                    l3GroupCounter++;
                    minWidth = order.getSetWidth();
                    maxWidth = order.getSetWidth();
                }
                order.setL3Group("L3G" + l3GroupCounter);
            }
            l3GroupCounter++;
        }
    }

    private static void generateL4Groups(List<Order> orders, List<GradeDetail> gradeDetails) {
        Map<String, GradeDetail> gradeDetailMap = gradeDetails.stream()
                .collect(Collectors.toMap(GradeDetail::getGrade, gd -> gd));

        Map<String, List<Order>> gradeGroupMap = orders.stream()
                .collect(Collectors.groupingBy(o -> gradeDetailMap.getOrDefault(o.getGrade(), new GradeDetail()).getGradeGrp()));

        int l4GroupCounter = 1;

        for (List<Order> gradeGroupOrders : gradeGroupMap.values()) {
            gradeGroupOrders.sort(Comparator.comparingInt(Order::getSetWidth).reversed());
            int previousWidth = -1;

            for (Order order : gradeGroupOrders) {
                if (previousWidth == -1 || previousWidth - order.getSetWidth() <= 25) {
                    order.setL4Group("L4G" + l4GroupCounter);
                } else {
                    l4GroupCounter++;
                    order.setL4Group("L4G" + l4GroupCounter);
                }
                previousWidth = order.getSetWidth();
            }
            l4GroupCounter++;
        }
    }


    private static void mapGradeDetailsToOrders(List<Order> orders, List<GradeDetail> gradeDetails) {
        Map<String, GradeDetail> gradeDetailMap = gradeDetails.stream()
                .collect(Collectors.toMap(GradeDetail::getGrade, gd -> gd));

        for (Order order : orders) {
            GradeDetail gradeDetail = gradeDetailMap.get(order.getGrade());
            if (gradeDetail != null) {
                order.setGradeGroup(gradeDetail.getGradeGrp());
                order.setVdType(gradeDetail.getVdType());
                order.setGradeType(gradeDetail.getGradeType());
                order.setRollingMill(gradeDetail.getRollingMill());
                order.setScrafingGroup(gradeDetail.getScrafingGroup());
            }
        }
    }

    private static void printOrders(List<Order> orders) {
//        System.out.println("Order_No | Order_Width | Set_Width | Grade | Delivery_Date | BTR_Qty | Product | L1_Group | L2_Group
//        | L3_Group | L4_Group | BTC_Qty | Bucket | Grade_Group | VD_TYPE | GRADE_TYPE | Rolling_MILL | Scrafing_Group");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
