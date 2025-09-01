package com.airtel;

import java.util.ArrayList;
import java.util.List;

public class PlanManager {
    private static List<Plan> plans = new ArrayList<>();

    static {
        // Add some default plans
        plans.add(new Plan("Airtel 299", 299, 5, 300, 50));
        plans.add(new Plan("Airtel 599", 599, 10, 500, 100));
        plans.add(new Plan("Airtel 999", 999, 20, 1000, 200));
    }

    public static List<Plan> getAllPlans() {
        return plans;
    }

    public static void addPlan(Plan p) {
        plans.add(p);
    }

    public static Plan getPlanByName(String name) {
        for(Plan p : plans) {
            if(p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
