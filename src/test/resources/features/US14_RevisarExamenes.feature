Feature: Revisar resultados de exámenes cargados por pacientes
  Como médico
  Quiero acceder a exámenes subidos por mis pacientes
  Para evaluar y ajustar tratamientos

  Scenario Outline: Acceso a exámenes del paciente
    Given el médico está en el perfil del paciente
    When accede a la sección "Exámenes médicos"
    Then el sistema muestra el archivo "<nombre_archivo>" disponible para descarga o visualización

    Examples:
      | nombre_archivo                |
      | analisis_sangre_abril.pdf     |
      | resultados_tiroides_marzo.jpg |
      | glucosa_enero_2025.pdf        |

  Scenario: No hay exámenes cargados
    Given el médico está en el perfil del paciente
    When accede a "Exámenes médicos"
    Then el sistema muestra "No hay exámenes cargados"