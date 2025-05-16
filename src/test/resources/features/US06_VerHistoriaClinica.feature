Feature: Visualizar historia clínica estructurada del paciente
  Como médico
  Quiero ver la historia clínica completa y ordenada
  Para brindar un diagnóstico más certero

  Scenario Outline: Mostrar historia clínica del paciente
    Given el médico está en el perfil de un paciente
    When selecciona "Historia clínica"
    Then el sistema muestra diagnósticos "<diagnostico>", tratamientos "<tratamiento>" y evolución "<evolucion>"

    Examples:
      | diagnostico                  | tratamiento                        | evolucion              |
      | Hipotiroidismo               | Levotiroxina 75mcg/día             | Mejorando              |
      | Diabetes Mellitus Tipo 2     | Metformina 500mg 2 veces al día    | Estable                |
      | Síndrome de Ovario Poliquístico | Anticonceptivos hormonales      | En seguimiento         |

  Scenario: Sin historia clínica registrada
    Given el médico está en el perfil de un paciente
    When selecciona "Historia clínica"
    Then el sistema muestra "No hay datos de historia clínica disponibles"