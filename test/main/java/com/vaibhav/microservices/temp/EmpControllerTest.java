import com.vaibhav.microservices.model.Employee;
import com.vaibhav.microservices.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmpControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmpController empController;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee("1", "John Doe", "john.doe@example.com");
        when(employeeService.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/employees/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

        verify(employeeService, times(1)).save(any(Employee.class));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("1", "John Doe", "john.doe@example.com"),
                new Employee("2", "Jane Smith", "jane.smith@example.com")
        );
        when(employeeService.allEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane.smith@example.com"));

        verify(employeeService, times(1)).allEmployees();
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee("1", "John Doe", "john.doe@example.com");
        when(employeeService.getEmployeeById("1")).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));

        verify(employeeService, times(1)).getEmployeeById("1");
    }

    @Test
    public void testDeleteEmployeeById() throws Exception {
        when(employeeService.deleteEmployeeById("1")).thenReturn("Employee deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/employees/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Employee deleted successfully"));

        verify(employeeService, times(1)).deleteEmployeeById("1");
    }
}