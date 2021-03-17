package com.zenghm.algorithm;

import java.util.*;

/**
 * @author Airlen
 * @date 2021/3/17
 * @description xxx
 * // 3. 查找常用字符
 * // 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 // 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * // 你可以按任意顺序返回答案。
 * <p>
 * // 示例 1：
 * // 输入：["bella","label","roller"]
 * // 输出：["e","l","l"]
 * <p>
 * // 示例 2：
 * // 输入：["cool","lock","cook"]
 * // 输出：["c","o"]
 *  
 * // 提示：
 * // 1 <= A.length <= 100
 * // 1 <= A[i].length <= 100
 * // A[i][j] 是小写字母
 */
public class FindChar {
    public String[] find(String[] arrary) {
        if (arrary == null || arrary.length == 0) {
            return new String[0];
        }
        ArrayList<Map<Character, Integer>> arrayList = new ArrayList<>();
        for (String s : arrary) {
            Map<Character, Integer> map = new HashMap<>();
            arrayList.add(map);
            for (int i = 0; i < s.length(); i++) {
                Character ch = s.charAt(i);
                if (!map.containsKey(ch)) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }
        }
        Map<Character, Integer> fisrtMap = arrayList.get(0);
        for (int index = 1; index < arrayList.size(); index++) {
            Set<Character> characters = in(fisrtMap.keySet(), arrayList.get(index).keySet());
            for (Character character : fisrtMap.keySet()) {
                if (!characters.contains(character)) {
                    fisrtMap.remove(character);
                } else {
                    fisrtMap.put(character,
                            fisrtMap.get(character) > arrayList.get(index).get(index)
                                    ? arrayList.get(index).get(index) : fisrtMap.get(character));
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry:fisrtMap.entrySet()){
            for(int i= 1;i <=entry.getValue();i++){
                result.add(entry.getKey().toString());
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public Set<Character> in(Set<Character> a, Set<Character> b) {
        Set<Character> r = new HashSet<>();
        for (Character character : a) {
            if (b.contains(character)) {
                r.add(character);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        FindChar findChar = new FindChar();
        System.out.println(findChar.find(new String[]{"",""}));
    }
}
