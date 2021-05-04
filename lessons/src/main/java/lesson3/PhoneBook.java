package lesson3;

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> PhoneBook_hm = new HashMap<>();
    private List<String> PhoneNumber_list;

    public void add(String surname, String phone_number) {
        if (PhoneBook_hm.containsKey(surname)) {
            PhoneNumber_list = PhoneBook_hm.get(surname);
            PhoneNumber_list.add(phone_number);
            PhoneBook_hm.put(surname, PhoneNumber_list);
        } else {
            PhoneNumber_list = new ArrayList<>();
            PhoneNumber_list.add(phone_number);
            PhoneBook_hm.put(surname, PhoneNumber_list);
        }
    }

    public List<String> get(String surname) {
        return PhoneBook_hm.get(surname);
    }
}
