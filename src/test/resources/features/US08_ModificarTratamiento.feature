Feature: Visualizar y modificar tratamiento farmacológico
  Como médico
  Quiero modificar el tratamiento del paciente
  Para ajustar la medicación según su evolución

  Scenario Outline: Modificar tratamiento existente
    Given el médico está en el tratamiento farmacológico del paciente
    When actualiza "<campo>" a "<valor>" y guarda
    Then el sistema confirma la actualización

    Examples:
      | campo         | valor                        |
      | Dosis         | 100mg                        |
      | Medicamento   | Metformina                   |
      | Frecuencia    | 2 veces al día               |

  Scenario: No modificar tratamiento
    Given el médico está en tratamiento farmacológico
    When no realiza cambios y cierra la pantalla
    Then el sistema mantiene el tratamiento anterior