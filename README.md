# mobility-poa

Esta API realiza operações em cima de uma API de linhas de ônibus e itinerários da cidade de Porto Alegre.

Linhas de ônibus - http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o
Itinerário de uma determinada unidade de transporte - http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566

Primeiramente, a aplicação possui dois endpoints para inserir os dados das APIs acima, sendo:

# GET /busline/add-all
- Insere todas linhas presentes da API poatransporte no banco e retorna a lista.

# GET /itinerary/add-client
- Insere o itinerário presente da API poatransporte no banco e o retorna.

# CRUDs de linha de ônibus:

# POST /busline
-Insere uma linha de ônibus.

# GET /busline
params: objeto BusLine
-Retorna todas as linhas cadastradas no banco.

# PUT /busline
params: objeto BusLine
-Edita uma linha de ônibus

# DELETE /busline
params: id
-Deleta uma linha de ônibus pelo id;


# CRUDs de Itinerário:

# POST /itinerary
-Insere um itinerário.

# GET /itinerary
params: objeto Itinerary
-Retorna todos itinerários cadastrados no banco.

# PUT /itinerary
params: objeto Itinerary
-Edita um itinerário

# DELETE /itinerary
params: idlinha
-Deleta um itinerário pelo id;


# Demais funcionalidades:

# GET /busline/per-name
params: nome
-retorna a lista de linhas de ônibus filtradas por nome.

# GET /busline/close-lines
params: latitude, longitude, raio
-retorna uma lista de linhas de ônibus dentro do raio de uma determinada coordenada de um ponto.

