import http from 'k6/http';


export const options = {
  stages: [
    { duration: '30s', target: 0 },
  ],
};

function generateCustomerId() {
  return Math.floor(100000 + Math.random() * 900000).toString();
}
export default function () {

  const customerId = generateCustomerId(); 

  const url = 'http://172.23.128.1:8080/orders';
  const payload = JSON.stringify({
    customerId: customerId, 
    amount: Math.random() * 1000, 
  });

  const params = {
    headers: { 'Content-Type': 'application/json' },
  };

  
  const res = http.post(url, payload, params);

  console.log(`Customer ID: ${customerId} - Response status: ${res.status}`);

}