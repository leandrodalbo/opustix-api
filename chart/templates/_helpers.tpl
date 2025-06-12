{{/*
Expand the name of the chart.
*/}}
{{- define "event-api.name" -}}
event-api
{{- end }}

{{/*
Create a default fully qualified app name.
*/}}
{{- define "event-api.fullname" -}}
{{ printf "%s-%s" .Release.Name (include "event-api.name" .) }}
{{- end }}
