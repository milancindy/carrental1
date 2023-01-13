import axios from '@/plugins/axios'


export function getcarlist() {
	return axios({
		url: 'carlist/getall',
		method: 'GET'
	})
}