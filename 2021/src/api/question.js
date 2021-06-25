import axios from "axios";
import {QUESTION_MODULE} from "@/api/_prefix";

export const insertQuestions = payload => {
    const {
                qnId,
                questionTitle,
                type,
                isMust,
                optionA,
                optionB,
                optionC,
                optionD,
                optionE,
                optionF
            }
    = payload
    return axios.post(`${QUESTION_MODULE}/insertQuestions`, {
                qnId,
                questionTitle,
                type,
                isMust,
                optionA,
                optionB,
                optionC,
                optionD,
                optionE,
                optionF
            }
    ).then(res => {
        return res.data
    })
}
export const getQuestions = payload => {
    // 根据qnid请求问题
    console.log(payload)
    return axios.get(`${QUESTION_MODULE}/getQuestions/${payload}`).then(res => {
        return res.data
    })
}