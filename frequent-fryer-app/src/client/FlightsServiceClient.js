const API_URL = 'http://localhost:8088';
const FLIGHTS_API = `${API_URL}/travelers/{travelerId}/flights/`;

export function getUserFlights(travelerId) {
    const userUrl = `${FLIGHTS_API}`.replace('{travelerId}', travelerId);
    return fetch(userUrl).then(response => response.json())
}