package services.services;

import java.util.LinkedHashSet;
import java.util.Set;

public class DepartamenteServices {
    private static DepartamenteServices departamenteServices;
    private DepartamenteServices(){}
    public static DepartamenteServices getDepartamenteServices(){
        if(departamenteServices==null)
            departamenteServices= new DepartamenteServices();
        return departamenteServices;
    }

    public Set<String> getDepartamente()
    {
        Set<String> departamente = new LinkedHashSet<>();
        departamente.add("Cardiologie");
        departamente.add("Neurologie");
        departamente.add("Chirurgie");
        departamente.add("Pediatrie");
        departamente.add("Ortopedie");
        return departamente;
    }
}
