import org.junit.Test;

import java.time.ZonedDateTime;

public class TestApplication {
    @Test
    public void Timezone(){
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
