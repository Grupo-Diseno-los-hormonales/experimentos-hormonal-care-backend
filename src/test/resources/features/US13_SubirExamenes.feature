Feature: Subir exámenes médicos
  Como paciente
  Quiero subir mis resultados médicos en la app
  Para que mi médico los pueda revisar

  Scenario: Subida exitosa de examen
    Given el paciente está autenticado
    When selecciona archivo válido y confirma subida
    Then el sistema almacena el examen y notifica al médico

  Scenario: Archivo inválido o error en subida
    Given el paciente está autenticado
    When selecciona archivo inválido o falla la subida
    Then el sistema muestra mensaje de error
