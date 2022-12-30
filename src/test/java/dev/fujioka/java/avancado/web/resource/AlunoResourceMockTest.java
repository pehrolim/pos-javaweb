package dev.fujioka.java.avancado.web.resource;

import dev.fujioka.java.avancado.web.model.Aluno;
import dev.fujioka.java.avancado.web.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AlunoResourceMockTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private AlunoRepository alunoRepository;

        @Test
        void deveriaCadastrarComSucesso() throws Exception{
            Aluno aluno = new Aluno();
            aluno.setNome("Pedro");
            aluno.setMatricula("12345");

            Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
            this.mvc.perform(MockMvcRequestBuilders.post("/aluno")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":null,\"nome\":\"Pedro\",\"matricula\":\"12345\"}")
                    .accept(MediaType.APPLICATION_JSON)
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nome").value("Pedro"))
                    .andExpect(jsonPath("$.matricula").value("12345"))
                    .andExpect(content().json("{'id':null,nome:'Pedro',matricula:'12345'}"));
        }

}
