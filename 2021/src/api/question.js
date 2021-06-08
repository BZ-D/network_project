import axios from "axios";

export const insertQuestions = payload => {
    console.log(payload)
    const {
        不太会写这个对象数组怎么传
    } = payload
    return axios.post('${QUESTION_MODULE}/insertQuestions', {
        不太会写这个对象数组怎么传
    }).then(res => {
        return res.data
    })
}
export const getQuestions = payload => {
    console.log(payload)
    return axios.post('${QUESTION_MODULE}/getQuestions/${qnId}').then(res => {
        return res.data
    })
}