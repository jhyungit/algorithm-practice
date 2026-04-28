import java.io.*;
import java.util.*;
// 우선순위
// 1. 가입자 최대한 늘리기
// 2. 판매액 최대한 늘리기
class Solution {
    static final int[] discounts = {40,30,20,10};
    static int[] selected;
    static int maxUseImoPlus;
    static int maxTotalIncome;

    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        // {40, 30, 20, 10} product 구하기
        selected = new int[emoticons.length];
        dfs(0, emoticons, users);
        
        answer[0] = maxUseImoPlus;
        answer[1] = maxTotalIncome;
        return answer;
    }
    
    static void dfs(int depth, int[] emoticons, int[][] users){
        if (depth == selected.length){
            // product 만들기
            check(selected, emoticons, users);
            return;
        }
        
        for(int d: discounts){
            selected[depth] = d;
            dfs(depth+1, emoticons, users);
        }
    }
    
    static void check(int[] discounts, int[] emoticons, int[][] users){
        int[] prices = new int[emoticons.length]; // 할인 후 가격 저장 리스트
        int[] spends = new int[users.length]; // 고객별 소비한 돈 리스트
        int useImoPlus = 0, totalIncome = 0;

        // 할인 후 가격 저장
        for(int i = 0; i < emoticons.length; i++){
            prices[i] = emoticons[i] * (100-discounts[i]) / 100;
        }
        
        for(int ui = 0; ui < users.length; ui++){
            for(int ei = 0; ei < emoticons.length; ei++){
                if(users[ui][0] <= discounts[ei]) spends[ui] += prices[ei];
            }
        }
        
        int j = 0;
        for(int spend: spends){
            if(spend >= users[j++][1]){ // 한도 이상일 때
                useImoPlus++;
            }else{
                totalIncome += spend;
            }
        }
        
        if(useImoPlus > maxUseImoPlus){
            maxUseImoPlus = useImoPlus;
            maxTotalIncome = totalIncome;
        }else if(useImoPlus == maxUseImoPlus && totalIncome > maxTotalIncome){
            maxTotalIncome = totalIncome;
        }
    }
}
