import http from 'k6/http';

const firstNames = ['Alice', 'Bob', 'Charlie', 'Diana', 'Eve'];
const lastNames = ['Smith', 'Johnson', 'Williams', 'Brown', 'Jones'];

export default function () {
  // Gera um nome completo aleatório
  const firstName = firstNames[Math.floor(Math.random() * firstNames.length)];
  const lastName = lastNames[Math.floor(Math.random() * lastNames.length)];
  const fullName = `${firstName} ${lastName}`;

  // Define a URL do endpoint e o payload da requisição
  const url = 'http://172.23.128.1:8080/orders';
  const payload = JSON.stringify({
    customerName: fullName,
    amount: Math.random() * 1000, // Valor aleatório para o pedido
  });

  // Define os cabeçalhos da requisição
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  // Envia a requisição POST
  const res = http.post(url, payload, params);

  // Exibe informações no console
  console.log(`Full Name: ${fullName}`);
  console.log(`Response status: ${res.status}`);
  console.log(`Response body: ${res.body}`);
}
