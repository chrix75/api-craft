package fr.arolla.api.craft;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.api.craft.etablissement.domain.service.EtablissementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateDesignationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EtablissementService etablissementService;

    @Test
    void updateDenominations() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/12345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"denominationUsuelle\": \"Arolla SAS\", \"enseigne\": \"Arolla\"}"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(
                "{\"denominationUsuelle\": \"Arolla SAS\", \"enseigne\": \"Arolla\"}");
    }

    @Test
    void invalidDenominationUsuel() throws Exception {
        String tooLongDenominationUsuelle = "A".repeat(101);
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/12345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"denominationUsuelle\": \"" + tooLongDenominationUsuelle + "\", \"enseigne\": \"Arolla\"}"))
                                     .andExpect(status().isBadRequest())
                                     .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(node.get("code").asText()).isEqualTo("denominationUsuelle.invalid");
    }

    @Test
    void emptyDenominationUsuelle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/12345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"denominationUsuelle\": \"\", \"enseigne\": \"Arolla\"}"))
                                     .andExpect(status().isBadRequest())
                                     .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(node.get("code").asText()).isEqualTo("denominationUsuelle.empty");
    }

    @Test
    void invalidSiret() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/A2345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"denominationUsuelle\": \"\", \"enseigne\": \"Arolla\"}"))
                                     .andExpect(status().isBadRequest())
                                     .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(node.get("code").asText()).isEqualTo("siret.invalid");
    }

    @Test
    void resetEnseigneValue() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/12345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"enseigne\": \"\"}"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(
                "{\"denominationUsuelle\": \"Arolla SAS\"}");
    }

    @Test
    void updateEnseigne() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/etablissement/12345678901234/denominations")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content("{\"enseigne\": \"Foo Bar\"}"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(
                "{\"denominationUsuelle\": \"Arolla SAS\", \"enseigne\": \"Foo Bar\"}");
    }

    @AfterEach
    void tearDown() {
        etablissementService.resetDesignation();
    }
}
